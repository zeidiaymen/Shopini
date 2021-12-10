import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ReclamationService } from 'src/app/Client/reclamationService/reclamation.service';

@Component({
  selector: 'app-update-reclamation',
  templateUrl: './update-reclamation.component.html',
  styleUrls: ['./update-reclamation.component.css']
})
export class UpdateReclamationComponent implements OnInit {
  @Input() reclamation !: any;
  //@Output() notif=new EventEmitter<any>();
  constructor(private service:ReclamationService) { }

  ngOnInit(): void {
  }
 
  update(recl:any){
   
  }
  sendNotifToParent(r:any){
 this.service.update(r,543 /*idp*/,11).subscribe(
   ()=>{
     console.log("modifier")
   }
 );
  }
}
