import { Component, OnInit } from '@angular/core';
import { Room, RoomType } from '../room.model';
import { RoomService } from '../room.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DeviceService } from '../../device/device.service';
import { RoomData } from '../roomData.model';

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
    // this.roomService.fetchDataFromBackend(this.room.id);
    // setInterval(() => {
    //   this.roomService.fetchDataFromBackend(this.room.id);
    // }, 2000);
  }

  
  ngOnInit(): void {
    
    this.route.paramMap.subscribe(params => {
      this.index = +params.get('index');
    });
    this.roomService.editModeChange.subscribe(
       (editMode: boolean) => {
      this.editMode = editMode;
    });
    // this.room = this.roomService.getRoom(this.index);
    this.roomService.getRoomById(this.index).subscribe(res=>{
      this.room = res;
      this.fetchData();
      setInterval(() => {
        this.fetchData();
      }, 10000);
    }, error=>{
      console.log(error);
    })
  }

  fetchData() {
      this.roomService.fetchDataFromBackend(this.room).subscribe((data: RoomData) => {
        console.log(data);
          this.room.co2 = data.co2Level;
          this.room.temperature = data.temperature;
          this.room.people = data.numOfPeople;
          this.room.dateTime = data.dateTime;
        });
}

  getRoomImagePath(roomType:RoomType): string {
    return this.roomService.getRoomImagePath(roomType);
  }

  onBack() {
    this.router.navigate(['../'], {relativeTo: this.route});
  }

  onEdit() {
    this.router.navigateByUrl(`/update-room/${this.index}`);
  }

  callStatusChanged(device) {
    this.deviceService.changeDeviceStatus(device);
  }

  onDelete() {
    //TODO: add a confirmation dialog
    this.roomService.deleteRoom(this.index).subscribe(res=>{
      this.router.navigate(['/home']);
    }, error=>{
      console.log(error);
    });
  }

  onAddDevice() {
    this.deviceService.editModeChanged(false);
    this.roomService.editModeChange.next(true);
    // const nextIndex = this.room.devices.length;
   // this.router.navigate(['/deviceEdit/'+ this.index + '/' + nextIndex]);
    this.router.navigateByUrl('/add-device/'+ this.room.id);
  }

  
  



}
