import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RoomService } from './room.service';
import { Room} from './room.model';

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

 /* it('should delete a room and update the list', () => {
    service.deleteRoom(1);
    const req = httpMock.expectOne(`${basePath}deleteRoom`);
    expect(req.request.method).toBe('POST');
    req.flush({});
    
    service.roomsChanged.subscribe(rooms => {
      expect(rooms.length).toBe(service.rooms.length - 1);
    });
  });*/

  // Additional tests for `getRooms`, `updateRoom`, `addDevice`, `updateDevice`, `deleteDevice`, etc.
});

