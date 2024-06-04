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
  @Input() roomId: number;
  @Input() device: Device;
  @Input() index: number = 0;

  constructor(private deviceService: DeviceService, private roomService : RoomService, private router: Router) { 
  }

  getDeviceImagePath(deviceType: DeviceType): string {
    return this.deviceService.getDeviceImagePath(deviceType);
  } 

  changeStatus(){
     this.deviceService.updateDeviceStatus(this.device.id).subscribe(res=>{
                this.device = res;
              }, error=>{
                console.log(error)
              });
  }

  onEdit() {
    this.deviceService.editModeChanged(true);
    this.router.navigate(['/deviceEdit/'+ this.roomId + '/' + this.device.id]);
  }

  onDelete() {
    //TODO: add a confirmation dialog
    console.log(this.device);
    this.deviceService.deleteDevice(this.device.id).subscribe(res=>{
      location.reload();
    }, error=>{
      console.log("delete error");
      console.log(error)
    });
  }
}
