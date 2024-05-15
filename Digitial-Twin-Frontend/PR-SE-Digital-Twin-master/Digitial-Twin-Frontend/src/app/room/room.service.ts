import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Room, RoomType, RoomTypeImagePath } from './room.model';
import { Device} from '../device/device.model';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class RoomService {

  devices: Device[];
  roomsChanged = new Subject<Room[]>();
  editModeChange = new Subject<Boolean>();
  //basePath = "https://digital-twin-backend/api/";
  basePath = "http://localhost:8080/room";

  constructor(private http: HttpClient) { }

   rooms: Room[] = [
    /*new Room(1, "bedroom 1", 10, 20, 60, 420, 2, [new Device(1,0, "Light","light 1",false), new Device(2,0, "Fan","fan 1",true),new Device(2,0, "Window","window 1",false),new Device(1,0, "Door","door 2",false),
    new Device(1,0, "Light","light 1",false), new Device(2,0, "Fan","fan 1",true),new Device(2,0, "Window","window 1",false),new Device(1,0, "Door","door 2",false),
    new Device(1,0, "Light","light 1",false), new Device(2,0, "Fan","fan 1",true),new Device(2,0, "Window","window 1",false),new Device(1,0, "Door","door 2",false)], "Bedroom"),
    new Room(2, "living room", 15, 25, 69, 420, 3, [new Device(2,0, "Fan","fan 1",true)], "LivingRoom"),
    new Room(3, "bathroom", 15, 25, 69, 420, 3, [new Device(2,0, "Window","window 1",false)], "Bathroom"),
    new Room(4, "gaming room", 15, 25, 69, 420, 3, [new Device(2,0, "Door","door 1",true)],  "Gaming"),
    new Room(5, "kitchen", 15, 25, 69, 420, 3, [new Device(2,0, "Fan","fan 2",false)],  "Kitchen"),
    new Room(6, "office", 15, 25, 69, 420, 3, [new Device(2,0, "Window"," window 2",true)],  "Office"),
    new Room(7, "bedroom 2", 10, 20, 60, 420, 2, [new Device(1,0, "Door","door 2",false)], "Bedroom")*/
  ];

  createEmptyRoom(): Room {
    return new Room(0, '', 0, 0, 0, 0, 0, [new Device(0,0, '', '', false)], '');
  }

  getRooms():Observable<Room[]>{
    return this.http.get<Room[]>(this.basePath);
  }

  getRoom(index: number): Room{
    //return this.http.get<Room>(this.basePath + "/" + index);
    return this.rooms[index];
  }

  getRoomImagePath(roomType: RoomType): string {
    return RoomTypeImagePath[roomType];
  }

  addRoom(room: Room){
    this.http.post<Room>(`${this.basePath}/addRoom`, room).subscribe({
      next: (response) => {
        console.log('Room added successfully', response);
        this.rooms.push(room);
        this.roomsChanged.next(this.rooms.slice());
      },
      error: (error) => {
        console.error('Error adding room', error);
      }
    });
  }

  updateRoom(index: number, newRoom: Room) {
    this.http.post(`${this.basePath}/updateRoom`,newRoom);
    this.rooms[index] = newRoom;
    this.roomsChanged.next(this.rooms.slice());
  }

  deleteRoom(index: number) {
    this.http.post(`${this.basePath}/deleteRoom`,this.rooms[index].id).subscribe((room: Room) => {
    this.rooms.splice(index, 1);
    this.roomsChanged.next(this.rooms.slice());
    });
  }

  editModeChanged(editMode: boolean){
    this.editModeChange.next(editMode);
  }

  getRoomStructure(){
    return this.http.get(this.basePath + '/report', { responseType: 'blob' });
  }

  /*Device Methods*/

  deleteDevice(roomIndex: number, deviceIndex : number) {
    this.http.post(`${this.basePath}/deleteDevice`,this.rooms[roomIndex].devices[deviceIndex]).subscribe((newDevice: Device) => {
    this.rooms[roomIndex].devices.splice(deviceIndex, 1);
    this.roomsChanged.next(this.rooms.slice());
  });
  }

  getDeviceWithIndex(roomIndex: number, deviceIndex: number): Device{
    return this.rooms[roomIndex].devices[deviceIndex];
  }

  addDevice(roomIndex: number, device: Device) {
    device.roomId = this.rooms[roomIndex].id;
    this.http.post(`${this.basePath}/addDevice`,device).subscribe((newDevice: Device) => {
      this.rooms[roomIndex].devices.push(newDevice);
      this.roomsChanged.next(this.rooms.slice());
     });
  }

  updateDevice(roomIndex: number, deviceIndex: number, newDevice: Device) {
    this.http.post(`${this.basePath}/updateDevice`,newDevice);
    this.rooms[roomIndex].devices[deviceIndex] = newDevice;
    this.roomsChanged.next(this.rooms.slice());
  }

  fetchDataFromBackend(roomId: number) {
    this.http.post(`${this.basePath}/getData`, roomId).subscribe((data: RoomData) => {
      let room = this.rooms.find(room => room.id === roomId);
        room.co2 = data.co2;
        room.temperature = data.temperature;
        room.people = data.people;
      });
      this.roomsChanged.next(this.rooms.slice());
  }



}

interface RoomData {
  co2: number;
  temperature: number;
  people: number;
}
