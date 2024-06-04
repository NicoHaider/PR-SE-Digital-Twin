import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { DeviceService } from '../device.service';
import { RoomService } from '../../room/room.service';

@Component({
  selector: 'app-device-edit',
  templateUrl: './device-edit.component.html',
  styleUrl: './device-edit.component.css'
})
export class DeviceEditComponent implements OnInit, OnDestroy{
  deviceId: number;
  deviceForm: FormGroup;
  subscription: Subscription;
  editMode = false;
  roomIndex : number;

  constructor(private route: ActivatedRoute,private router: Router, private roomService: RoomService,private deviceService: DeviceService) { 
    this.editMode = this.deviceService.getEditMode();  
  }

  ngOnInit() {
    this.subscription = this.deviceService.editModeChange.subscribe(
      (editMode : boolean) => {
        this.editMode = editMode;
      }
    );
    this.route.params.subscribe(
        (params: Params) => {
          this.roomIndex = +params['roomIndex'];
          this.deviceId = +params['index'];
          this.initForm();
        }
    );
  }
  
  private initForm() {
    let deviceName = '';
    let deviceType = '';
    let deviceStatus = false;

    //get data of the device (prepopulate the form)
    if (this.editMode) {
      this.deviceService.getDeviceWithId(this.deviceId).subscribe(res=>{
        console.log("device with id " + this.deviceId, res);
        deviceType = res.deviceType;
        deviceName = res.name;
        deviceStatus = res.status;
        console.log("deviceType", deviceType);
        console.log("deviceName", deviceName);
        console.log("deviceStatus", deviceStatus);
      }, error=>{
        console.log("error getting device with id " + this.deviceId, error)
      });
    }

    this.deviceForm = new FormGroup({
      'name': new FormControl(deviceName, Validators.required),
      'type': new FormControl(deviceType, Validators.required),
      'status': new FormControl(deviceStatus, Validators.required)
    });
  }

  onSubmit() {
    if (this.editMode) {
      const data = this.deviceForm.value;
      data.roomId = this.roomIndex;
      console.log("data", data);
      this.deviceService.updateDevice(data).subscribe(res=>{
        this.router.navigate(['/', this.roomIndex]);
      }, error=>{
        console.log(error)
      });
    } 
  }
  
  onCancel() {
    this.router.navigate(['/', this.roomIndex]);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
