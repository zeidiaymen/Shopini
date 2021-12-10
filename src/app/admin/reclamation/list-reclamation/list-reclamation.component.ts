import { Component, OnInit } from '@angular/core';
import { ReclamationService } from 'src/app/Client/reclamationService/reclamation.service';

@Component({
  selector: 'app-list-reclamation',
  templateUrl: './list-reclamation.component.html',
  styleUrls: ['./list-reclamation.component.css']
})
export class ListReclamationComponent implements OnInit {
list:any=[];
show:boolean=false;
recl:any;
  constructor(private service:ReclamationService) { }

  ngOnInit(): void {
    this.service.listRelamations().subscribe(
      (d)=>{
        this.list=d;
      }
    )
  }



  getRecl(rec:any){
    if(this.show==false){
      this.show=true;
    }
  else this.show=false;
this.recl=rec;
console.log(this.recl)
  }
  update(r:any){
    this.service.update(r,543 /*idp*/,11 /*idUser*/).subscribe();
  }
  delete(id:any){
this.service.delete(id).subscribe(
  ()=>{
    console.log("deleted")
  }
)
  }

}
