
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
  name: string;
  type: DeviceType;
  status: boolean;

  constructor(id: number, type: string, name: string, status: boolean) {
    this.id = id;
    this.type = DeviceType[type as keyof typeof DeviceType];
    this.name = name;
    this.status = status;
  }
}
