import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { Device, DeviceType } from '../device.model';
import { DeviceService } from '../device.service';
import { Room } from '../../room/room.model';
import { RoomService } from '../../room/room.service';

@Component({
  selector: 'app-device-edit',
  templateUrl: './device-edit.component.html',
  styleUrl: './device-edit.component.css'
})
export class DeviceEditComponent implements OnInit, OnDestroy{
  index: number;
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
          this.index = +params['index'];
          this.initForm();
        }
    );
  }
  

  private initForm() {
    console.log(this.editMode);
    let deviceName = '';
    let deviceType = '';
    let deviceStatus = false;

    //get data of the device (prepopulate the form)
    if (this.editMode) {
      console.log(this.editMode);
      const device = this.roomService.getDeviceWithIndex(this.roomIndex, this.index);
      console.log(device);
      deviceType = device.type;
      deviceName = device.name;
      deviceStatus = device.status;
    }

    this.deviceForm = new FormGroup({
      'name': new FormControl(deviceName, Validators.required),
      'type': new FormControl(deviceType, Validators.required),
      'status': new FormControl(deviceStatus, Validators.required)
    });
  }

  onSubmit() {
    if (this.editMode) {
      console.log(this.deviceForm.value);
      this.roomService.updateDevice(this.roomIndex,this.index, this.deviceForm.value);
    } else {
      console.log(this.deviceForm.value);
      this.roomService.addDevice(this.roomIndex, this.deviceForm.value);
      
    }
    this.onCancel();
  }
  
  onCancel() {
    this.router.navigate(['/', this.roomIndex]);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
