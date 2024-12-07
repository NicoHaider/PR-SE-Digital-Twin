import { Injectable } from '@angular/core';
import { Device, DeviceImagePath, DeviceType } from './device.model';
import { Observable} from 'rxjs';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class DeviceService {

  basePath = "http://localhost:8080/device";
  
  constructor(private http: HttpClient) { }

  createEmptyDevice(): Device {
    return new Device(0, 0, '', '', false);
  }

  deleteDevice(deviceId : number) {
    return this.http.get(this.basePath + `/delete/${deviceId}` );
  }

  getDeviceWithId(deviceId: number): Observable<any>{
    return this.http.get(this.basePath + `/get/${deviceId}` );
  }

  addDevice(device: any):Observable<any>{
    return this.http.post(this.basePath, device);
  }

  updateDeviceStatus(id: number): Observable<any>{
    return this.http.get(this.basePath + `/${id}`);
  }

  updateDevice(device: any): Observable<any>{
    return this.http.post(this.basePath + "/update", device);
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

