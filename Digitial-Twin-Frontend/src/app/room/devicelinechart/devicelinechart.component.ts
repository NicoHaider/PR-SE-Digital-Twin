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
    selector: 'app-linechart',
    templateUrl: './devicelinechart.component.html',
    styleUrl: './devicelinechart.component.css'
  })
export class DevicelinechartComponent implements OnInit{ 
@ViewChild("chart") chart: ChartComponent;
public chartOptions: Partial<ChartOptions>;

constructor() {
  this.chartOptions = {
    series: [
      {
        name: "Session Duration",
        data: [45, 52, 38, 24, 33, 26, 21, 20, 6, 8, 15, 10]
      },
      {
        name: "Page Views",
        data: [35, 41, 62, 42, 13, 18, 29, 37, 36, 51, 32, 35]
      },
      {
        name: "Total Visits",
        data: [87, 57, 74, 99, 75, 38, 62, 47, 82, 56, 45, 47]
      }
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
      curve: "straight",
      dashArray: [0, 8, 5]
    },
    title: {
      text: "Page Statistics",
      align: "left"
    },
    legend: {
      tooltipHoverFormatter: function(val, opts) {
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
      categories: [
        "01 Jan",
        "02 Jan",
        "03 Jan",
        "04 Jan",
        "05 Jan",
        "06 Jan",
        "07 Jan",
        "08 Jan",
        "09 Jan",
        "10 Jan",
        "11 Jan",
        "12 Jan"
      ]
    },
    tooltip: {
      y: [
        {
          title: {
            formatter: function(val) {
              return val + " (mins)";
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
      ngOnInit(): void {
          throw new Error('Method not implemented.');
      }
}
