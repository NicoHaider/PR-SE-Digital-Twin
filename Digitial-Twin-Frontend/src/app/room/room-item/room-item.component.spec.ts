import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CommonModule } from '@angular/common';
import { RoomItemComponent } from './room-item.component';
import { RoomService } from '../room.service';
import { of } from 'rxjs';

describe('RoomItemComponent', () => {
  let component: RoomItemComponent;
  let fixture: ComponentFixture<RoomItemComponent>;
  let mockRoomService: Partial<RoomService>;

  beforeEach(async () => {
    // Create the mock using Jest
    mockRoomService = {
      getRooms: jest.fn().mockReturnValue(of([])),
      createEmptyRoom: jest.fn().mockReturnValue({}) // Make sure to mock this method if it's used in the component
    };

    await TestBed.configureTestingModule({
      declarations: [RoomItemComponent],
      imports: [CommonModule],
      providers: [
        { provide: RoomService, useValue: mockRoomService }
      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(RoomItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
