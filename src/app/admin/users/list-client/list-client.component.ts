import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/shared/userService/user.service';

@Component({
  selector: 'app-list-client',
  templateUrl: './list-client.component.html',
  styleUrls: ['./list-client.component.css']
})
export class ListClientComponent implements OnInit {

  constructor(private userService: UserService) { }

  clients: any;
  
  changeStatusButton="";

  ngOnInit(): void {
    

    let resp = this.userService.getClients();
    resp.subscribe((data) => this.clients = data);   

     }


    

     verifUserOrNot(email:any){
      const formData = new FormData();
      formData.append('email',email);
      this.userService.verifUserOrNot(formData).subscribe(data=>{

        let resp = this.userService.getClients();
    resp.subscribe((data) => this.clients = data);          

      })


       
      
     }

     


}
