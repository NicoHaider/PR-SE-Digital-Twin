import { Component, Input } from '@angular/core';
import { Device, DeviceType } from '../device.model';
import { DeviceService} from '../device.service';
import { Router } from '@angular/router';
import { Room } from '../../room/room.model';
import { RoomService } from '../../room/room.service';

@Component({
  selector: 'app-device-item',
  templateUrl: './device-item.component.html',
  styleUrl: './device-item.component.css'
})
export class DeviceItemComponent {
  @Input() roomIndex: number;
  @Input() device: Device;
  @Input() index: number = 0;

  constructor(private deviceService: DeviceService, private roomService : RoomService, private router: Router) { 
  }

  getDeviceImagePath(deviceType: DeviceType): string {
    return this.deviceService.getDeviceImagePath(deviceType);
  } 

  changeStatus(){
    this.device.status = !this.device.status;
  }

  onEdit() {
    this.deviceService.editModeChanged(true);
    this.router.navigate(['/deviceEdit/'+ this.roomIndex + '/' + this.index]);
  }

  onDelete() {
    //TODO: add a confirmation dialog
    console.warn('are you sure you want to delete this device?');
    this.roomService.deleteDevice(this.roomIndex, this.index);
  }
}
