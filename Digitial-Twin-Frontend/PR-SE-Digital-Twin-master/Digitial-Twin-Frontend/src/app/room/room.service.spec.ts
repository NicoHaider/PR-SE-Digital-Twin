import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RoomService } from './room.service';
import { Device, Room, RoomType, RoomTypeImagePath} from './room.model';

describe('RoomService', () => {
  let service: RoomService;
  let httpMock: HttpTestingController;
  let basePath: string = 'https://digital-twin-backend/api/';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [RoomService]
    });
    service = TestBed.inject(RoomService);
    httpMock = TestBed.inject(HttpTestingController);
  });

 
  afterEach(() => {
    httpMock.verify(); // Verify that there are no outstanding requests
  });



  it('should create an empty room correctly', () => {
    const emptyRoom = service.createEmptyRoom();
    expect(emptyRoom.id).toEqual(0);
    expect(emptyRoom.devices.length).toEqual(1);
  });

  it('should add a room and emit the change', () => {
    const newRoom: Room = new Room(8, "test room", 10, 20, 50, 300, 2, [], "TestRoom");
    service.addRoom(newRoom);

    const req = httpMock.expectOne(`${basePath}addRoom`);
    expect(req.request.method).toBe('POST');
    req.flush(newRoom);

    service.roomsChanged.subscribe((rooms) => {
      expect(rooms).toContain(newRoom);
    });
  });

it('should get all rooms correctly', () => {
  const rooms = service.getRooms();
  expect(rooms.length).toEqual(service.rooms.length);
  expect(rooms).toEqual(service.rooms);
  const req = httpMock.expectOne(`${basePath}getAllRooms`);
  expect(req.request.method).toBe('GET');
  req.flush(service.rooms);
});

it('should get a specific room correctly', () => {
  const roomIndex = 0;
  const room = service.getRoom(roomIndex);
  expect(room).toEqual(service.rooms[roomIndex]);
});

it('should get the room image path correctly', () => {
  const roomType = RoomType.Bedroom;
  const imagePath = service.getRoomImagePath(roomType);
  expect(imagePath).toEqual(RoomTypeImagePath[roomType]);
});

it('should update a room correctly', () => {
  const roomIndex = 0;
  const updatedRoom: Room = new Room(1, "updated room", 10, 20, 60, 420, 2, [], "Bedroom");
  service.updateRoom(roomIndex, updatedRoom);
  expect(service.rooms[roomIndex]).toEqual(updatedRoom);
});

it('should delete a room and update the list', () => {
  const defaultRoom = new Room(1, "Default Room", 10, 20, 50, 300, 2, [
    new Device(1, 1, "Fan", "Fan 1", true)
  ], "LivingRoom");
  service.rooms = [defaultRoom];  
  const roomIndex = 0;
  const roomToDelete = service.getRoom(roomIndex);
  service.deleteRoom(roomIndex);
  const req = httpMock.expectOne(`${basePath}deleteRoom`);
  expect(req.request.method).toBe('POST');
  req.flush({});
  service.roomsChanged.subscribe(rooms => {
    expect(rooms.length).toBe(service.rooms.length - 1);
    expect(rooms).not.toContain(roomToDelete);
  });
});


it('should fetch data from the backend correctly', () => {
  const roomId = 1;
  service.fetchDataFromBackend(roomId);
  const req = httpMock.expectOne(`${basePath}getData`);
  expect(req.request.method).toBe('POST');
});



it('should emit the edit mode change', () => {
  const editMode = true;
  service.editModeChange.subscribe((mode) => {
    expect(mode).toBe(editMode);
  });
  service.editModeChanged(editMode);
});

it('should emit the edit mode change with false', () => {
  const editMode = false;
  service.editModeChange.subscribe((mode) => {
    expect(mode).toBe(editMode);
  });
  service.editModeChanged(editMode);
});

/*Device Tests*/

it('should get a device with index correctly', () => {
  const defaultRoom = new Room(2, "living room", 15, 25, 69, 420, 3, [new Device(2,0, "Fan","fan 1",true)], "LivingRoom");
  service.rooms = [defaultRoom]; 
  const roomIndex = 0;
  const deviceIndex = 0;
  const device = service.getDeviceWithIndex(roomIndex, deviceIndex);
  expect(device).toEqual(service.rooms[roomIndex].devices[deviceIndex]);
});

it('should add a device to a room correctly', () => {
  const defaultRoom = new Room(2, "living room", 15, 25, 69, 420, 3, [new Device(2,0, "Fan","fan 1",true)], "LivingRoom");
  service.rooms = [defaultRoom]; 
  const roomIndex = 0;
  const newDevice: Device = new Device(3, 2, "AC", "ac 1", false);
  service.addDevice(roomIndex, newDevice);
  const req = httpMock.expectOne(`${basePath}addDevice`);
  expect(req.request.method).toBe('POST');
  req.flush(newDevice);
  expect(service.rooms[roomIndex].devices.length).toBe(2);
  expect(service.rooms[roomIndex].devices[1]).toEqual(newDevice);
});

it('should update a device in a room correctly', () => {
  const defaultRoom = new Room(2, "living room", 15, 25, 69, 420, 3, [new Device(2,0, "Fan","fan 1",true)], "LivingRoom");
  service.rooms = [defaultRoom]; 
  const roomIndex = 0;
  const deviceIndex = 0;
  const updatedDevice: Device = new Device(2, 0, "Light", "light 2", true);
  service.updateDevice(roomIndex, deviceIndex, updatedDevice);
  expect(service.rooms[roomIndex].devices[deviceIndex]).toEqual(updatedDevice);
});

it('should delete a device from a room correctly', () => {
  const defaultRoom = new Room(2, "living room", 15, 25, 69, 420, 3, [new Device(2,0, "Fan","fan 1",true)], "LivingRoom");
  service.rooms = [defaultRoom];  
  const roomIndex = 0;
  const deviceIndex = 1;
  service.deleteDevice(roomIndex, deviceIndex);
  //test
  const req = httpMock.expectOne(`${basePath}deleteDevice`);
  expect(req.request.method).toBe('POST');
  req.flush({});
  expect(service.rooms[roomIndex].devices.length).toBe(1);
});

});

