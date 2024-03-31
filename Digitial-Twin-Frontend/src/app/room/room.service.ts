import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Room, RoomType, RoomTypeImagePath } from './room.model';
import { Device} from '../device/device.model.component';

@Injectable()
export class RoomService {
  roomsChanged = new Subject<Room[]>();
  editModeChange = new Subject<Boolean>();

  private rooms: Room[] = [
    new Room(1, "bedroom 1", 10, 20, 60, 420, 2, [new Device(1, "Light","light 1",false), new Device(2, "Fan","fan 1",true),new Device(2, "Window","window 1",false),new Device(1, "Door","door 2",false),
    new Device(1, "Light","light 1",false), new Device(2, "Fan","fan 1",true),new Device(2, "Window","window 1",false),new Device(1, "Door","door 2",false),
    new Device(1, "Light","light 1",false), new Device(2, "Fan","fan 1",true),new Device(2, "Window","window 1",false),new Device(1, "Door","door 2",false)], "Bedroom"),
    new Room(2, "living room", 15, 25, 69, 420, 3, [new Device(2, "Fan","fan 1",true)], "LivingRoom"),
    new Room(3, "bathroom", 15, 25, 69, 420, 3, [new Device(2, "Window","window 1",false)], "Bathroom"),
    new Room(4, "gaming room", 15, 25, 69, 420, 3, [new Device(2, "Door","door 1",true)],  "Gaming"),
    new Room(5, "kitchen", 15, 25, 69, 420, 3, [new Device(2, "Fan","fan 2",false)],  "Kitchen"),
    new Room(6, "office", 15, 25, 69, 420, 3, [new Device(2, "Window"," window 2",true)],  "Office"),
    new Room(7, "bedroom 2", 10, 20, 60, 420, 2, [new Device(1, "Door","door 2",false)], "Bedroom")
  ];

  createEmptyRoom(): Room {
    return new Room(0, '', 0, 0, 0, 0, 0, [new Device(0, '', '', false)], '');
  }

  getRooms():Room[]{
    return this.rooms.slice();
  }

  getRoom(index: number): Room{
    return this.rooms[index];
  }

  getRoomImagePath(roomType: RoomType): string {
    return RoomTypeImagePath[roomType];
  } 

  addRoom(room: Room){
    this.rooms.push(room);
    return this.roomsChanged.next(this.rooms.slice());
  }

  updateRoom(id: number, newRoom: Room) {
    this.rooms[id] = newRoom;
    this.roomsChanged.next(this.rooms.slice());
  }

  deleteRoom(id: number) {
    this.rooms.splice(id, 1);
    this.roomsChanged.next(this.rooms.slice());
  }

  editModeChanged(editMode: boolean){
    this.editModeChange.next(editMode);
  }

  deleteDevice(roomIndex: number, deviceIndex : number) {
    this.rooms[roomIndex].devices.splice(deviceIndex, 1);
    this.roomsChanged.next(this.rooms.slice());
  }

}
