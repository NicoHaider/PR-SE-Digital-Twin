import { Component, Input, OnInit } from '@angular/core';
import {
  ApexAxisChartSeries,
  ApexTitleSubtitle,
  ApexDataLabels,
  ApexFill,
  ApexMarkers,
  ApexYAxis,
  ApexXAxis,
  ApexTooltip,
  ApexStroke
} from "ng-apexcharts";
import { Room } from '../room.model';
import { RoomData } from '../roomData.model';
import { RoomService } from '../room.service';

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: any; //ApexChart;
  dataLabels: ApexDataLabels;
  markers: ApexMarkers;
  title: ApexTitleSubtitle;
  fill: ApexFill;
  yaxis: ApexYAxis;
  xaxis: ApexXAxis;
  tooltip: ApexTooltip;
  stroke: ApexStroke;
  grid: any; //ApexGrid;
  colors: any;
  toolbar: any;
};

@Component({
  selector: 'app-linechart',
  templateUrl: './linechart.component.html',
  styleUrl: './linechart.component.css'
})
export class LinechartComponent implements OnInit{
  @Input() roomId: number;
  room: Room;
  roomData: RoomData[];

  public chart1options: Partial<ChartOptions>;
  public chart2options: Partial<ChartOptions>;
  public chart3options: Partial<ChartOptions>;
  public commonOptions: Partial<ChartOptions> = {
    dataLabels: {
      enabled: false
    },
    stroke: {
      curve: "smooth"
    },
    toolbar: {
      tools: {
        selection: false
      }
    },
    markers: {
      size: 4,
      hover: {
        size: 10
      }
    },
    tooltip: {
      followCursor: false,
      theme: "dark",
      x: {
        show: true
      },
      marker: {
        show: false
      },
      y: {
        title: {
          formatter: function() {
            return "";
          }
        }
      }
    },
    grid: {
      clipMarkers: false
    },
    xaxis: {
      type: "datetime"
    },

  };

  constructor(private roomService: RoomService) {
   
  }

  ngOnInit(): void {
    this.roomService.getRoomById(this.roomId).subscribe((room: Room) => {
      this.room = room;
      this.fetchData();

      setInterval(() => {
        this.fetchextraData();
      }, 10000);
      
    });
  }
  

  public initCharts(): void {
    this.chart1options = {
      series: [
        {
          name: "People Chart",
          data: this.generateGraphData( "numOfPeople" )
        }
      ],
      chart: {
        id: "fb",
        group: "social",
        type: "line",
        height: 250
      },
      colors: ["#008FFB"],
      yaxis: {
        tickAmount: 2,
        labels: {
          minWidth: 40
        }
      },
        title: {
          text: "People Chart",
          align: "left",
          offsetX: 31,

          style: {
            fontSize: "12px",
            fontWeight: "bold",
            color: "#333",
        }
  
      }
    };


    this.chart2options = {
      series: [
        {
          name: "Temperature Chart",
          data: this.generateGraphData( "temperature" )
        }
      ],
      chart: {
        id: "tw",
        group: "social",
        type: "line",
        height: 250
      },
      colors: ["#546E7A"],
      yaxis: {
        tickAmount: 2,
        labels: {
          minWidth: 40
        }
      },
      title: {
        text: "Temperature Chart",
        align: "left",
        offsetX: 31,
        style: {
          fontSize: "12px",
          fontWeight: "bold",
          color: "#333",
        }
      }
    };

    this.chart3options = {
      series: [
        {
          name: "Co2 Chart",
          data: this.generateGraphData( "co2Level" )
        }
      ],
      chart: {
        id: "yt",
        group: "social",
        type: "area",
        height: 250
      },
      colors: ["#00E396"],
      yaxis: {
        tickAmount: 2,
        labels: {
          minWidth: 40
        }
      },
      title: {
        text: "Co2 Chart",
        align: "left",
        offsetX: 31,
        style: {
          fontSize: "12px",
          fontWeight: "bold",
          color: "#333",
        }
      }
    };
  }

  public updateSeries() {
    this.chart1options.series = [
      {
        name: "People Chart",
        data: this.generateGraphData( "numOfPeople" )
      }
    ];
    this.chart2options.series = [
      {
        name: "Temperature Chart",
        data: this.generateGraphData( "temperature" )
      }
    ];
    this.chart3options.series = [
      {
        name: "Co2 Chart",
        data: this.generateGraphData( "co2Level" )
      }
    ];
     
  }

  public generateGraphData(property): any[] {
    let i = 0;
    let series = [];
    let count = this.roomData.length;
    while (i < count) {
      var x = this.roomData[i].dateTime;
      var y = this.roomData[i][property];
      series.push([x, y]);
      i++;
    }
    return series;
  }

  fetchData() {
    this.roomService.fetchAllDataFromBackend(this.room).subscribe((data: RoomData[]) => {
      this.roomData = data;
      this.initCharts();
      }, error=>{
        console.log("error in fetch all ", error)
      }); 
  }


  fetchextraData() {
    this.roomService.fetchAllDataFromBackend(this.room).subscribe((data: RoomData[]) => {
      this.roomData = data;
      this.updateSeries();
      }, error=>{
        console.log("error in fetch all ", error)
      });
  }
  
}
