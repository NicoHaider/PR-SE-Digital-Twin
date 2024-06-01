import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DeviceService } from '../device.service';

@Component({
  selector: 'app-add-device',
  templateUrl: './add-device.component.html',
  styleUrl: './add-device.component.css'
})
export class AddDeviceComponent {

   deviceForm: FormGroup;
   roomId = this.route.snapshot.params['id'];

    constructor(private route: ActivatedRoute,private router: Router,private deviceService: DeviceService) { 
       this.deviceForm = new FormGroup({
            'name': new FormControl(null, Validators.required),
            'status': new FormControl(null, Validators.required),
            'deviceType': new FormControl(null, Validators.required)
          }); 
    }

      onSubmit() {
        if(this.deviceForm.valid){
          console.log(this.deviceForm.value);
          const data = this.deviceForm.value;
          data.roomId = this.roomId;
          this.deviceService.addDevice(data).subscribe(res=>{
            this.onCancel();
          }, error=>{
            console.log(error)
          });
        }
      }
      
      onCancel() {
        this.router.navigate(['/', this.roomId]);
      }

}
