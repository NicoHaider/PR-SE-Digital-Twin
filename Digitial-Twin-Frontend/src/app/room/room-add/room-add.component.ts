import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { RoomService } from '../room.service';

@Component({
  selector: 'app-room-add',
  templateUrl: './room-add.component.html',
  styleUrl: './room-add.component.css'
})
export class RoomAddComponent implements OnInit{
  index: number;
  editMode = false;
  roomForm: FormGroup;

  constructor(private route: ActivatedRoute,private roomService: RoomService,private router: Router) {   
  }

  ngOnInit() {
    this.route.params.subscribe(
        (params: Params) => {
          this.index = +params['index'];
          this.editMode = params['index'] != null;
          this.initForm();
        }
    );
  }

  private initForm() {
    let roomName = '';
    let roomType = '';
    let roomSize = 0;

    this.roomForm = new FormGroup({
      'name': new FormControl(roomName, Validators.required),
      'type': new FormControl(roomType, Validators.required),
      'size': new FormControl(roomSize, [Validators.required, Validators.pattern(/^[1-9]+[0-9]*$/)]),
      'deviceDtoList': new FormArray([])
    });
  }

    addDevice() {
      const deviceFormGroup = new FormGroup({
        'name': new FormControl(null, Validators.required),
        'status': new FormControl(null, Validators.required),
        'deviceType': new FormControl(null, Validators.required)
      });
  
      (<FormArray>this.roomForm.get('deviceDtoList')).push(deviceFormGroup);
    }
  onSubmit() {
    this.roomService.addRoom(this.roomForm.value).subscribe(res=>{
      this.router.navigate(['/home']).then(() => {
        location.reload();
      });
    }, error=>{
      console.log("error", error)
    });
  }
  
  onCancel() {
    this.router.navigate(['../'], {relativeTo: this.route});
  }

}
