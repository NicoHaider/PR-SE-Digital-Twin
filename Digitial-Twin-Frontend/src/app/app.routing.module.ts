import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoomAddComponent } from './room/room-add&Edit/room-add.component';
import { RoomListComponent } from './room/room-list/room-list.component';
import { RoomDetailsComponent } from './room/room-details/room-details.component';
import { DeviceEditComponent } from './device/device-edit/device-edit.component';
import { AddDeviceComponent } from './device/add-device/add-device.component';
import { UpdateRoomComponent } from './room/update-room/update-room.component';
import { AutomationRuleComponent } from './room/automation-rule/automation-rule.component';
import { AddAutomationRuleComponent } from './room/automation-rule/add-automation-rule/add-automation-rule.component';
import { AutomationRulesOverviewComponent } from './room/automation-rule/automation-rules-overview/automation-rules-overview.component';



const appRoutes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: RoomListComponent },
  { path: 'addRoom', component: RoomAddComponent },
  { path: ':index', component: RoomDetailsComponent },
  { path: ':index/edit', component: RoomAddComponent },
  { path: 'deviceEdit/:roomIndex/:index', component: DeviceEditComponent },
  { path: 'add-device/:id', component: AddDeviceComponent },
  { path: 'update-room/:id', component: UpdateRoomComponent },
  { path: 'rules', component: AutomationRuleComponent },
  { path: 'add-automation-rule', component: AddAutomationRuleComponent },
  { path: 'automation-rules-overview', component: AutomationRulesOverviewComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
