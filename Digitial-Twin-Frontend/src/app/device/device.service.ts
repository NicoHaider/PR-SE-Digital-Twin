import { Injectable } from '@angular/core';
import { Device, DeviceImagePath, DeviceType } from './device.model.component';
import { RoomService } from '../room/room.service';
import { Subject } from 'rxjs';

@Injectable()
export class DeviceService {
  editModeChange = new Subject<Boolean>();
  
  constructor(private roomService : RoomService) { }

  createEmptyDevice(): Device {
    return new Device(0, '', '', false);
  }

  getDevice(index: number) {
    return this.roomService.getRoom(index).devices;
  }

  getDeviceImagePath(deviceType: DeviceType): string {
    return DeviceImagePath[deviceType];
  } 

  changeDeviceStatus(device: Device){
    device.status = !device.status;
  }
  
  editModeChanged(editMode: boolean){
    this.editModeChange.next(editMode);
  }

}
