import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { Device, DeviceType } from '../../device/device.model';
import { Room } from '../room.model';
import { RoomService } from '../room.service';

@Component({
  selector: 'app-update-room',
  templateUrl: './update-room.component.html',
  styleUrl: './update-room.component.css'
})
export class UpdateRoomComponent {
index: number;
  editMode = false;
  roomForm: FormGroup;
  private roomBefore: Room;
  subscription: Subscription;
   roomId = this.route.snapshot.params['id'];


  constructor(private route: ActivatedRoute,private roomService: RoomService,private router: Router) {  
  }

  ngOnInit() {
    this.initForm();
  }

  private initForm() {

    this.roomForm = new FormGroup({
      'name': new FormControl(null, Validators.required),
      'type': new FormControl(null, Validators.required),
      'size': new FormControl(null, [Validators.required, Validators.pattern(/^[1-9]+[0-9]*$/)]),
    });

    this.roomService.getRoomById(this.roomId).subscribe(res=>{
          this.roomForm.patchValue(res);
        }, error=>{
          console.log(error);
        })
  }

  onSubmit() {
    // const newRoom = this.convertFormToRoom(this.roomForm.value);
    console.log(this.roomForm.value);
    console.log(this.roomForm.valid);
    if (this.roomForm.valid) {
      // this.roomService.updateRoom(this.index, newRoom);
      const data = this.roomForm.value;
      data.id = this.roomId;
      this.roomService.updateRoom(data).subscribe(res=>{
        this.router.navigateByUrl(`/${this.roomId}`);
      }, error=>{
        console.log("error", error)
      });
       
      
    }
    this.onCancel();
  }
  
  onCancel() {
    this.router.navigate(['../'], {relativeTo: this.route});
  }


  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
