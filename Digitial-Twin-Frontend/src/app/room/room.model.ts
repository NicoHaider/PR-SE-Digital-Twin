import { Device } from '../device/device.model';
import {AutomationRule} from "./automation-rule/automation-rules.model";


export enum RoomType {
  Livingroom = 'Livingroom',
  Bedroom = 'Bedroom',
  Gaming = 'Gaming',
  Bathroom = 'Bathroom',
  Office = 'Office',
  Kitchen = 'Kitchen',
}

export const RoomTypeImagePath = {
  [RoomType.Livingroom]: '../../assets/images/livingroom.png',
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
  deviceDtoList: Device[];
  type: RoomType;
  dateTime: Date = new Date();
  automationRuleDtoList: AutomationRule[];

  constructor(id: number, name: string, size: number, temperature: number, humidity: number, co2: number, people: number, devices: Device[], type: string, automationRuleDtoList: AutomationRule[]) {
    this.id = id;
    this.name = name;
    this.size = size;
    this.temperature = temperature;
    this.humidity = humidity;
    this.co2 = co2;
    this.people = people;
    this.deviceDtoList = devices;
    this.type = RoomType[type as keyof typeof RoomType];
    this.automationRuleDtoList = automationRuleDtoList;
  }



}

