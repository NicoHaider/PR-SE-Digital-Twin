import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { RoomService } from '../room.service';
import { Room } from '../room.model';
import {Router } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from '../../app.routing.module';

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
   this.rooms = this.roomService.getRooms();
  }

  addRoom() {
    this.router.navigate(['addRoom']);
  }

}
