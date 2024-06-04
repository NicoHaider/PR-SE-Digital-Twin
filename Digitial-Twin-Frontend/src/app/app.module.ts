import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { RoomItemComponent } from './room/room-item/room-item.component';
import { RoomListComponent } from './room/room-list/room-list.component';
import { RoomService } from './room/room.service';
import { RoomAddComponent } from './room/room-add&Edit/room-add.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app.routing.module';
import { RoomDetailsComponent } from './room/room-details/room-details.component';
import { DeviceItemComponent } from './device/device-item/device-item.component';
import { DeviceService } from './device/device.service';
import { DeviceEditComponent } from './device/device-edit/device-edit.component';
import { HttpClientModule } from '@angular/common/http';
import { AddDeviceComponent } from './device/add-device/add-device.component';
import { UpdateRoomComponent } from './room/update-room/update-room.component';
import { LinechartComponent } from './room/linechart/linechart.component';
import { NgApexchartsModule } from 'ng-apexcharts';

@NgModule({
  declarations: [
    AppComponent,
    RoomItemComponent,
    RoomListComponent,
    RoomAddComponent,
    RoomDetailsComponent,
    DeviceItemComponent,
    DeviceEditComponent,
    AddDeviceComponent,
    UpdateRoomComponent,
    LinechartComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    NgApexchartsModule
  ],
  providers: [RoomService, DeviceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
