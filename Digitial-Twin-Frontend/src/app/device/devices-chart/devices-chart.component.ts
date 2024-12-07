import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Room } from '@app/room/room.model';
import { RoomService } from '@app/room/room.service';
import { DeviceStatusData } from '../device-status-data';

@Component({
  selector: 'app-devices-chart',
  templateUrl: './devices-chart.component.html',
  styleUrl: './devices-chart.component.css'
})
export class DevicesChartComponent implements OnInit{
  roomId: number;
  room: Room; 
  deviceStatusData: DeviceStatusData[];

  constructor(private roomService: RoomService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.roomId = params['roomId'];
    });
    this.roomService.getRoomById(this.roomId).subscribe(res=>{
      this.room = res;
    });
  }

  fetchData() {
  this.roomService.fetchAllDeviceDataFromBackend(this.room).subscribe((data: DeviceStatusData[]) => {
    this.deviceStatusData = data;
    this.createChart();
    }, error=>{
      console.log("Error fetching device data ", error)
    });
    
  }

  public createChart(): void {
  }

  onBack() {
    this.router.navigateByUrl('/'+this.roomId);
  }
  
}
