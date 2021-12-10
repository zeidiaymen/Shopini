import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/shared/userService/user.service';
import { LivreurService } from '../service/livreur.service';

@Component({
  selector: 'app-list-livreur',
  templateUrl: './list-livreur.component.html',
  styleUrls: ['./list-livreur.component.css']
})
export class ListLivreurComponent implements OnInit {

  constructor(private livreurService: LivreurService,private userService:UserService) { }

  livreurs: any;
  inputSearch:any;

  
  childComponentClicked=false;
  checkChildConfirmButton=false;
  changeStatusButton="";

  ngOnInit(): void {
    

    let resp = this.livreurService.getLivreurs();
    resp.subscribe((data) => this.livreurs = data);   

     }


     changePourcentage(){
      this.childComponentClicked=true;


     }

     verifUserOrNot(email:any){
      const formData = new FormData();
      formData.append('email',email);
      this.userService.verifUserOrNot(formData).subscribe(data=>{

        let resp = this.livreurService.getLivreurs();
    resp.subscribe((data) => this.livreurs = data);          

      })


       
      
     }

     checkChildConfirm(data:any){
       this.checkChildConfirmButton=data;
      
     }




search()
    {
      if(this.inputSearch=="")
      {
        this.ngOnInit();
      }
      else {
        this.livreurs.filter((res: { code: string; })=>{
          return res.code.toLocaleLowerCase().match(this.inputSearch.toLocaleLowerCase)
      })
      }
    }

  }

