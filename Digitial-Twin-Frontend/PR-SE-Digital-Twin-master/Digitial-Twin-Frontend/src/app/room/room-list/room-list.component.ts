import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { RoomService } from '../room.service';
import { Room } from '../room.model';
import {Router } from '@angular/router';
import { HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrl: './room-list.component.css',
  providers: [RoomService]
})
export class RoomListComponent implements OnInit{

  rooms : Room[] = [];
  subscription : Subscription;

  constructor(private roomService: RoomService,private router: Router, private hhtpClient: HttpClient ) {
  }

  ngOnInit() {
    this.subscription = this.roomService.roomsChanged.subscribe(
      (rooms: Room[]) => {
        this.rooms = rooms;
      }
    );
    this.roomService.getRooms().subscribe({
      next: (rooms: Room[]) => {
        console.log("Rooms = ", rooms);
        this.rooms = rooms;
      },
      error: (error) => {
        console.error('Error fetching rooms', error);
      }
    });
  }

  addRoom() {
    this.router.navigate(['addRoom']);
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
