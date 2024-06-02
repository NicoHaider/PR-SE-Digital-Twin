import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { RoomService } from '../room.service';
import { Room } from '../room.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrl: './room-list.component.css'
})
export class RoomListComponent implements OnInit{

  rooms : Room[] = [];

  constructor(private roomService: RoomService,private router: Router) {
  }

  ngOnInit() {
    this.getRooms();
  }

  addRoom() {
    this.router.navigate(['addRoom']);
  }

  getRooms(){
    this.roomService.getRooms().subscribe(res=>{
      this.rooms = res;
      console.log(res);
     }, error=>{
      console.log("error", error)
     });
  }

  onDownloadRoom(){
    this.roomService.getRoomStructure().subscribe({
      next: (blob: Blob) => {
        const a = document.createElement('a');
        const objectUrl = URL.createObjectURL(blob);
        a.href = objectUrl;
        a.download = 'room_report.csv';
        a.click();
        URL.revokeObjectURL(objectUrl);
      },
      error: (error) => {
        console.error('Error getting room file', error);
      }
    });
}
}
