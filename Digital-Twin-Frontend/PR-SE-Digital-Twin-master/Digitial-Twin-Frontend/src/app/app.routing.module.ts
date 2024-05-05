import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoomAddComponent } from './room/room-add&Edit/room-add.component';
import { RoomListComponent } from './room/room-list/room-list.component';
import { RoomDetailsComponent } from './room/room-details/room-details.component';
import { DeviceEditComponent } from './device/device-Add&Edit/device-edit.component';


export const appRoutes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: RoomListComponent },
  { path: 'addRoom', component: RoomAddComponent },
  { path: ':index', component: RoomDetailsComponent },
  { path: ':index/edit', component: RoomAddComponent }, 
  { path: 'deviceEdit/:roomIndex/:index', component: DeviceEditComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}