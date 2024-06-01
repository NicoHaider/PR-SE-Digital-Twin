import { Injectable } from '@angular/core';
import { Device, DeviceImagePath, DeviceType } from './device.model';
import { RoomService } from '../room/room.service';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class DeviceService {

  // basePath = "https://digital-twin-backend/api/";
  basePath = "http://localhost:8080/";

  
  editModeChange = new BehaviorSubject<boolean>(false);
  
  constructor(private roomService : RoomService,
    private http: HttpClient) { }

  createEmptyDevice(): Device {
    return new Device(0, 0, '', '', false);
  }

    addDevice(device: any):Observable<any>{
      return this.http.post(this.basePath + "device", device);
    }
  
      updateDeviceStatus(id: number): Observable<any>{
        return this.http.get(this.basePath + `device/${id}`);
      }

  getDevices(index: number) : Device[] {
    // return this.roomService.getRoom(index).devices;
    return [];
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
    this.http.post(this.basePath + 'updateDevice',Device);
    device.status = !device.status;
  }

  getDevice(){
    return this.http.get('http://localhost:8080/device');
  }
}

