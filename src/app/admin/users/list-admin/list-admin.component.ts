import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/shared/userService/user.service';
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-list-admin',
  templateUrl: './list-admin.component.html',
  styleUrls: ['./list-admin.component.css']
})
export class ListAdminComponent implements OnInit {

  constructor(private adminService: AdminService ,private userService:UserService) { }

  admins: any;
  
  changeStatusButton="";

  ngOnInit(): void {
    

    let resp = this.adminService.getAdmins();
    resp.subscribe((data) => this.admins = data);   

     }


    

     verifUserOrNot(email:any){
      const formData = new FormData();
      formData.append('email',email);
      this.userService.verifUserOrNot(formData).subscribe(data=>{

        let resp = this.adminService.getAdmins();
    resp.subscribe((data) => this.admins = data);          

      })


       
      
     }

     


}
