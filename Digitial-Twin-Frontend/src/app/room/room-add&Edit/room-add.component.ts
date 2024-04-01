import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { RoomService } from '../room.service';
import { Room, RoomType } from '../room.model';
import { Device, DeviceType } from '../../device/device.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-room-add',
  templateUrl: './room-add.component.html',
  styleUrl: './room-add.component.css'
})
export class RoomAddComponent implements OnInit, OnDestroy{
  index: number;
  editMode = false;
  roomForm: FormGroup;
  private roomBefore: Room;
  subscription: Subscription;


  constructor(private route: ActivatedRoute,private roomService: RoomService,private router: Router) {
    this.roomBefore = roomService.createEmptyRoom();    
  }

  ngOnInit() {
    this.subscription = this.roomService.editModeChange.subscribe(
      (editMode : boolean) => {
        this.editMode = editMode;
      }
    );
    this.route.params.subscribe(
        (params: Params) => {
          this.index = +params['index'];
          this.editMode = params['index'] != null;
          this.roomService.editModeChange.next(this.editMode);
          this.initForm();
        }
    );
  }

  private initForm() {
    let roomName = '';
    let roomType = '';
    let roomSize = 0;
    let roomLight = 0;
    let roomFans = 0;
    let roomDoors = 0;
    let roomWindows = 0;

    //get data of the room (prepopulate the form)
    if (this.editMode) {
      const room = this.roomService.getRoom(this.index);
      this.roomBefore = room;
      roomType = room.type;
      roomName = room.name;
      roomSize = room.size;
    }

    this.roomForm = new FormGroup({
      'name': new FormControl(roomName, Validators.required),
      'type': new FormControl(roomType, Validators.required),
      'size': new FormControl(roomSize, [Validators.required, Validators.pattern(/^[1-9]+[0-9]*$/)]),
      'light': new FormControl(roomLight, [Validators.required, Validators.pattern(/^[0-9]+[0-9]*$/)]),
      'fans': new FormControl(roomFans, [Validators.required, Validators.pattern(/^[0-9]+[0-9]*$/)]),
      'doors': new FormControl(roomDoors, [Validators.required, Validators.pattern(/^[0-9]+[0-9]*$/)]),
      'windows': new FormControl(roomWindows, [Validators.required, Validators.pattern(/^[0-9]+[0-9]*$/)])
    });
  }

  onSubmit() {
    const newRoom = this.convertFormToRoom(this.roomForm.value);
    if (this.editMode) {
      this.roomService.updateRoom(this.index, newRoom);
    } else {
      this.roomService.addRoom(newRoom);
      
    }
    this.onCancel();
  }
  
  onCancel() {
    this.router.navigate(['../'], {relativeTo: this.route});
  }

  private convertFormToRoom(formValue: any): Room {
    const id = this.roomBefore.id;
    const name = formValue.name;
    const size = formValue.size;
    const temperature = this.roomBefore.temperature;
    const humidity = this.roomBefore.humidity;
    const co2 = this.roomBefore.co2;
    const people = this.roomBefore.people;
    const devices = this.updateDevices(formValue);
    const type = formValue.type;

    return new Room(id, name, size, temperature, humidity, co2, people, devices, type);
  }

  private updateDevices(formValue: any): Device[] {
    const devices: Device[] = [];
    const beforeDevices = this.roomBefore.devices;

    const deviceTypes = [DeviceType.Light, DeviceType.Fan, DeviceType.Window, DeviceType.Door];

    deviceTypes.forEach(type => {
      const beforeDeviceCount = this.getDeviceCount(beforeDevices, type);
      const currentDeviceCount = formValue[type.toLowerCase()];
      const deviceDifference = currentDeviceCount - beforeDeviceCount;

      if (deviceDifference > 0) {
        for (let i = 0; i < deviceDifference; i++) {
          devices.push(new Device(0,0, type, '', false));
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

   // get roomControls() {
  //   return (this.roomForm.get('ingredients') as FormArray).controls
  // }

  // onAddIngredient() {
  //   (<FormArray>this.roomForm.get('ingredients')).push(
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
  //   (<FormArray>this.roomForm.get('ingredients')).removeAt(index);
  // }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
