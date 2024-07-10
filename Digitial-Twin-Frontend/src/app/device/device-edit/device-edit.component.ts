import { Component,OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { DeviceService } from '../device.service';
import { RoomService } from '../../room/room.service';

@Component({
  selector: 'app-device-edit',
  templateUrl: './device-edit.component.html',
  styleUrl: './device-edit.component.css'
})
export class DeviceEditComponent implements OnInit{
  deviceId: number;
  deviceForm: FormGroup;
  roomIndex : number;

  constructor(private route: ActivatedRoute,private router: Router, private roomService: RoomService,private deviceService: DeviceService) { 
  }

  ngOnInit() {
    this.route.params.subscribe(
        (params: Params) => {
          this.roomIndex = +params['roomIndex'];
          this.deviceId = +params['index'];
          this.initForm();
        }
    );
  }
  
  private initForm() {
    this.deviceForm = new FormGroup({
      'name': new FormControl(null, Validators.required),
      'type': new FormControl(null, Validators.required)
    });

    //get data of the device (prepopulate the form)
    this.deviceService.getDeviceWithId(this.deviceId).subscribe(res=>{
      this.deviceForm.patchValue({
        name: res.name,
        type: res.deviceType
      });
    }, error=>{
      console.log("error getting device with id " + this.deviceId, error)
    });   
  }

  onSubmit() {
    const data = this.deviceForm.value;
    data.roomId = this.roomIndex;
    console.log("data", data);
    this.deviceService.updateDevice(data).subscribe(res=>{
      this.router.navigate(['/', this.roomIndex]);
    }, error=>{
      console.log(error)
    });
    
  }
  
  onCancel() {
    this.router.navigate(['/', this.roomIndex]);
  }

}
