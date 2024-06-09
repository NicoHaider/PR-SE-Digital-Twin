import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { RoomService } from '../room.service';
import { Room } from '../room.model';
import { ActivatedRoute, Router } from '@angular/router';
import { RoomData } from '../roomData.model';


@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrl: './room-list.component.css'
})
export class RoomListComponent implements OnInit{
  [x: string]: any;

  rooms : Room[] = [];

  constructor(private roomService: RoomService,private router: Router) {
  }


  ngOnInit() {
    this.getRooms();
    this.fetchData();
    setInterval(() => {
    this.fetchData();
  }, 10000);
  }

  fetchData() {
      this.rooms.forEach(room => {
        this.roomService.fetchDataFromBackend(room).subscribe((data: RoomData) => {
          console.log(data);
            room.co2 = data.co2Level;
            room.temperature = data.temperature;
            room.people = data.numOfPeople;
            room.dateTime = data.dateTime;
            console.log("room after fetching data "+ room.co2);
          });
      });
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
