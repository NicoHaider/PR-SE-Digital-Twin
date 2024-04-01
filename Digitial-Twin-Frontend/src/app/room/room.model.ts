import { Device } from '../device/device.model';

export enum RoomType {
  LivingRoom = 'Living Room',
  Bedroom = 'Bedroom',
  Gaming = 'Gaming',
  Bathroom = 'Bathroom',
  Office = 'Office',
  Kitchen = 'Kitchen',
}

export const RoomTypeImagePath = {
  [RoomType.LivingRoom]: '../../assets/images/livingroom.png',
  [RoomType.Bedroom]: '../../assets/images/bedroom.png',
  [RoomType.Gaming]: '../../assets/images/gameroom.png',
  [RoomType.Bathroom]: '../../assets/images/bath.png',
  [RoomType.Office]: '../../assets/images/office.png',
  [RoomType.Kitchen]: '../../assets/images/kitchen.png',
};

export class Room {
  id: number = 0;
  name: string;
  size: number;
  temperature: number;
  humidity: number;
  co2: number;
  people: number;
  devices: Device[];
  type: RoomType;

  constructor(id: number, name: string, size: number, temperature: number, humidity: number, co2: number, people: number, devices: Device[], type: string) {
    this.id = id;
    this.name = name;
    this.size = size;
    this.temperature = temperature;
    this.humidity = humidity;
    this.co2 = co2;
    this.people = people;
    this.devices = devices;
    this.type = RoomType[type as keyof typeof RoomType];
  }

}

