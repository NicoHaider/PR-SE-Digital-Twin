import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { Device, DeviceType } from '../device.model.component';
import { DeviceService } from '../device.service';
import { Room } from '../../room/room.model';

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

  constructor(private route: ActivatedRoute,private deviceService: DeviceService,private router: Router) {   
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
          console.log(this.roomIndex);
          this.index = +params['index'];
          console.log(this.index);
          //this.initForm();
        }
    );
  }
  
/*
  private initForm() {
    let deviceName = '';
    let deviceType = '';
    let deviceStatus = false;

    //get data of the device (prepopulate the form)
    if (this.editMode) {
      const device = this.deviceService.getDevice(this.index);
      deviceType = device.type;
      deviceName = device.name;
      deviceSize = device.size;
      const deviceDevices = device.devices;
      for (let device of deviceDevices) {
        if (device.type === 'Light') {
          deviceLight++;
        } else if (device.type === 'Fan') {
          deviceFans++;
        } else if (device.type === 'Door') {
          deviceDoors++;
        } else if (device.type === 'Window') {
          deviceWindows++;
        }
      }

      // get each device and prepopulate the form

      // if (device['devices']) {
      //   for (let ingredient of device.ingredients) {
      //     deviceIngredients.push(
      //       new FormGroup({
      //         'name': new FormControl(ingredient.name, Validators.required),
      //         'amount': new FormControl(ingredient.amount, [
      //           Validators.required,
      //           Validators.pattern(/^[1-9]+[0-9]*$/)
      //         ])
      //       })
      //     );
      //   }
      //}
    }

    this.deviceForm = new FormGroup({
      'name': new FormControl(deviceName, Validators.required),
      'type': new FormControl(deviceType, Validators.required),
      'size': new FormControl(deviceSize, [Validators.required, Validators.pattern(/^[1-9]+[0-9]*$/)]),
      'light': new FormControl(deviceLight, [Validators.required, Validators.pattern(/^[0-9]+[0-9]*$/)]),
      'fans': new FormControl(deviceFans, [Validators.required, Validators.pattern(/^[0-9]+[0-9]*$/)]),
      'doors': new FormControl(deviceDoors, [Validators.required, Validators.pattern(/^[0-9]+[0-9]*$/)]),
      'windows': new FormControl(deviceWindows, [Validators.required, Validators.pattern(/^[0-9]+[0-9]*$/)])
    });
  }

  onSubmit() {
    const newdevice = this.convertFormTodevice(this.deviceForm.value);
    if (this.editMode) {
      this.deviceService.updatedevice(this.index, newdevice);
    } else {
      this.deviceService.adddevice(newdevice);
      
    }
    this.onCancel();
  }
  
  onCancel() {
    this.router.navigate(['../'], {relativeTo: this.route});
  }

  private convertFormTodevice(formValue: any): device {
    const id = this.deviceBefore.id;
    const name = formValue.name;
    const size = formValue.size;
    const temperature = this.deviceBefore.temperature;
    const humidity = this.deviceBefore.humidity;
    const co2 = this.deviceBefore.co2;
    const people = this.deviceBefore.people;
    const devices = this.updateDevices(formValue);
    const type = formValue.type;

    return new device(id, name, size, temperature, humidity, co2, people, devices, type);
  }

  private updateDevices(formValue: any): Device[] {
    const devices: Device[] = [];
    const beforeDevices = this.deviceBefore.devices;

    const deviceTypes = [DeviceType.Light, DeviceType.Fan, DeviceType.Window, DeviceType.Door];

    deviceTypes.forEach(type => {
      const beforeDeviceCount = this.getDeviceCount(beforeDevices, type);
      const currentDeviceCount = formValue[type.toLowerCase()];
      const deviceDifference = currentDeviceCount - beforeDeviceCount;

      if (deviceDifference > 0) {
        for (let i = 0; i < deviceDifference; i++) {
          devices.push(new Device(0, type, '', false));
        }
      } else if (deviceDifference < 0) {
        const devicesToRemove = beforeDevices.filter(device => device.type === type).slice(0, Math.abs(deviceDifference));
        devices.push(...devicesToRemove);
      } else {
        devices.push(...beforeDevices.filter(device => device.type !== type));
      }
    });

    return devices;
  }

  private getDeviceCount(devices: Device[], type: DeviceType): number {
    return devices.filter(device => device.type === type).length;
  }

  private getDeviceIndex(devices: Device[], type: DeviceType): number {
    return devices.findIndex(device => device.type === type);
  }

   // get deviceControls() {
  //   return (this.deviceForm.get('ingredients') as FormArray).controls
  // }

  // onAddIngredient() {
  //   (<FormArray>this.deviceForm.get('ingredients')).push(
  //     new FormGroup({
  //       'name': new FormControl(null, Validators.required),
  //       'amount': new FormControl(null, [
  //         Validators.required,
  //         Validators.pattern(/^[1-9]+[0-9]*$/)
  //       ])
  //     })
  //   );
  // }

  // onDeleteIngredient(index: number) {
  //   (<FormArray>this.deviceForm.get('ingredients')).removeAt(index);
  // }
*/
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
