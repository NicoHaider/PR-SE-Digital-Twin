import { Component, Input, OnInit, ViewChild } from '@angular/core';
import {
  ApexAxisChartSeries,
  ApexTitleSubtitle,
  ApexDataLabels,
  ApexFill,
  ApexMarkers,
  ApexYAxis,
  ApexXAxis,
  ApexTooltip,
  ApexStroke,
  ChartComponent
} from "ng-apexcharts";
import { Room } from '../room.model';
import { RoomData } from '../roomData.model';
import { RoomService } from '../room.service';
import { DeviceStatusData } from '@app/device/device-status-data';

export type ChartOptions = {
    series: ApexAxisChartSeries;
    chart: any;
    dataLabels: ApexDataLabels;
    markers: ApexMarkers;
    title: ApexTitleSubtitle;
    fill: ApexFill;
    yaxis: ApexYAxis;
    xaxis: ApexXAxis;
    tooltip: ApexTooltip;
    stroke: ApexStroke;
    grid: any; 
    colors: any;
    toolbar: any;
    legend: any; 
  };
  
  @Component({
    selector: 'devicelinechart',
    templateUrl: './devicelinechart.component.html',
    styleUrl: './devicelinechart.component.css'
  })
export class DevicelinechartComponent implements OnInit{ 
@ViewChild("chart") chart: ChartComponent;
public chartOptions: Partial<ChartOptions>;
@Input() roomId: number;
room: Room;
deviceStatusData: DeviceStatusData[];

constructor(private roomService: RoomService) {
   
}

ngOnInit(): void {
    this.roomService.getRoomById(this.roomId).subscribe((room: Room) => {
      this.room = room;
      this.fetchData();     
    });
  }

public initCharts(): void {
  this.chartOptions = {
    series: [
        {
            name: "Opend Windows",
            data: this.generateGraphData("windows_on")
        },
        {
            name: "Turned On Lights",
            data: this.generateGraphData("lights_on")
        },
        {
            name: "Opend Doors",
            data: this.generateGraphData("doors_on")
        },
        {
            name: "Turned On Fans",
            data: this.generateGraphData("fans_on")         }
            ],
      chart: {
      height: 350,
      type: "line"
    },
    dataLabels: {
      enabled: false
    },
    stroke: {
      width: 5,
      curve: "stepline"
    },
    title: {
      text: "Devicelinechart",
      align: "left"
    },
    legend: {
      tooltipHoverFormatter: function( val, opts) {
        return (
          val +
          " - <strong>" +
          opts.w.globals.series[opts.seriesIndex][opts.dataPointIndex] +
          "</strong>"
        );
      }
    },
    markers: {
      size: 0,
      hover: {
        sizeOffset: 6
      }
    },
    xaxis: {
      labels: {
        trim: false
      },
      type: "datetime"
   
    },
    tooltip: {
      y: [
        {
          title: {
            formatter: function(val) {
              return val ;
            }
          }
        },
        {
          title: {
            formatter: function(val) {
              return val + " per session";
            }
          }
        },
        {
          title: {
            formatter: function(val) {
              return val;
            }
          }
        }
      ]
    },
    grid: {
      borderColor: "#f1f1f1"
    }
  };
}

 generateGraphData(property): any[] {
    let i = 0;
    let series = [];
    let count = this. deviceStatusData.length;
    while (i < count) {
      var x = this.deviceStatusData[i].dateTime;
      var y = this.deviceStatusData[i][property];
      series.push([x, y]);
      i++;
    }
    return series;
  }

fetchData() {
    this.roomService.fetchAllDeviceDataFromBackend(this.room).subscribe((data: DeviceStatusData[]) => {
      this.deviceStatusData = data;
      console.log("device data", this.deviceStatusData);
      this.initCharts();
      }, error=>{
        console.log("error in fetch all ", error)
      }); 
  }

}



