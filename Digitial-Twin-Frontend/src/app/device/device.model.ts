
export enum DeviceType {
  Light = 'Light',
  Fan = 'Fan',
  Window = 'Window',
  Door = 'Door'
}

export const DeviceImagePath = {
  [DeviceType.Light]: '../../assets/images/light.png',
  [DeviceType.Fan]: '../../assets/images/fan.png',
  [DeviceType.Window]: '../../assets/images/window.png',
  [DeviceType.Door]: '../../assets/images/door.png'
};

export class Device {
  id: number;
  roomId: number;
  name: string;
  deviceType: DeviceType;
  status: boolean;

  constructor(id: number, roomId: number, type: string, name: string, status: boolean) {
    this.id = id;
    this.roomId = roomId;
    this.deviceType = DeviceType[type as keyof typeof DeviceType];
    this.name = name;
    this.status = status;
  }
}
