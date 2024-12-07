import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoomAddComponent } from './room/room-add/room-add.component';
import { RoomListComponent } from './room/room-list/room-list.component';
import { RoomDetailsComponent } from './room/room-details/room-details.component';
import { DeviceEditComponent } from './device/device-edit/device-edit.component';
import { AddDeviceComponent } from './device/add-device/add-device.component';
import { UpdateRoomComponent } from './room/update-room/update-room.component';
import {AutomationRulesComponent} from "./room/automation-rule/automation-rules.component";
import {AddAutomationRuleComponent} from "./room/automation-rule/add-automation-rule/add-automation-rule.component";
import {EditAutomationRuleComponent} from "./room/automation-rule/edit-automation-rule/edit-automation-rule.component";
import { DevicesChartComponent } from './device/devices-chart/devices-chart.component';


const appRoutes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: RoomListComponent },
  { path: 'addRoom', component: RoomAddComponent },
  { path: 'rules/:id', component: AutomationRulesComponent },
  { path: 'add-rule/:id', component: AddAutomationRuleComponent},
  { path: 'update-rule/:roomId/:id', component: EditAutomationRuleComponent},
  { path: 'deviceEdit/:roomIndex/:index', component: DeviceEditComponent },
  { path: 'add-device/:id', component: AddDeviceComponent },
  { path: 'update-room/:id', component: UpdateRoomComponent },
  { path: ':index', component: RoomDetailsComponent },
  { path: ':index/edit', component: RoomAddComponent },
  { path: 'devicesChart/:roomId', component: DevicesChartComponent},
  { path: '**', redirectTo: '/home'},
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
