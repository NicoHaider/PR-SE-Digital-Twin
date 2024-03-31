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
  subscription : Subscription;

  constructor(private roomService: RoomService,private router: Router) {
  }

  ngOnInit() {
    this.subscription = this.roomService.roomsChanged.subscribe(
      (rooms: Room[]) => {
        this.rooms = rooms;
      }
    );
   this.rooms = this.roomService.getRooms();
  }

  addRoom() {
    this.router.navigate(['addRoom']);
  }

}
