import { Component, OnInit } from '@angular/core';
import { Room, RoomType } from '../room.model';
import { RoomService } from '../room.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DeviceService } from '../../device/device.service';

@Component({
  selector: 'app-room-details',
  templateUrl: './room-details.component.html',
  styleUrl: './room-details.component.css'
})
export class RoomDetailsComponent implements OnInit{
  room: Room;
  index: number;
  editMode : boolean = false;

  constructor(private roomService: RoomService, private deviceService: DeviceService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.index = +params.get('index');
    });
    this.roomService.editModeChange.subscribe(
       (editMode: boolean) => {
      this.editMode = editMode;
    });
    this.room = this.roomService.getRoom(this.index);
  }

  getRoomImagePath(roomType:RoomType): string {
    return this.roomService.getRoomImagePath(roomType);
  }

  onBack() {
    this.router.navigate(['../'], {relativeTo: this.route});
  }

  onEdit() {
    this.router.navigate(['edit'], {relativeTo: this.route});
    this.roomService.editModeChange.next(true);
  }

  callStatusChanged(device) {
    this.deviceService.changeDeviceStatus(device);
  }

  onDelete() {
    //TODO: add a confirmation dialog
    this.roomService.deleteRoom(this.index);
    this.router.navigate(['/home']);
  }

  onAddDevice() {
    this.deviceService.editModeChanged(false);
    this.roomService.editModeChange.next(true);
    const nextIndex = this.room.devices.length;
    this.router.navigate(['/deviceEdit/'+ this.index + '/' + nextIndex]);
  }
  


}
