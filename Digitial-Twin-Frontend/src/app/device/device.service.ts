import { Injectable } from '@angular/core';
import { Device, DeviceImagePath, DeviceType } from './device.model';
import { RoomService } from '../room/room.service';
import { BehaviorSubject, Observable, Subject } from 'rxjs';

@Injectable()
export class DeviceService {

  basePath = "https://digital-twin-backend/api/";

  //constructor(private http: HttpClient) { }

  
  editModeChange = new BehaviorSubject<boolean>(false);
  
  constructor(private roomService : RoomService) { }

  createEmptyDevice(): Device {
    return new Device(0, 0, '', '', false);
  }

  getDevices(index: number) : Device[] {
    return this.roomService.getRoom(index).devices;
  }

  getEditMode(): boolean {
    return this.editModeChange.getValue();
  }

  editModeChanged(editMode: boolean){
    this.editModeChange.next(editMode);
  }

  getDeviceImagePath(deviceType: DeviceType): string {
    return DeviceImagePath[deviceType];
  }

  changeDeviceStatus(device: Device) {
    // this.http.post(this.basePath + 'updateDevice',Device);
    device.status = !device.status;
  }
}

