import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { LivreurService } from '../service/livreur.service';

@Component({
  selector: 'app-change-livreur',
  templateUrl: './change-livreur.component.html',
  styleUrls: ['./change-livreur.component.css']
})
export class ChangeLivreurComponent implements OnInit {
  @Input()hero!: any;
  @Output() parentFunction:EventEmitter<any>=new EventEmitter();

  constructor(private livreurService: LivreurService) { }
  ngOnInit(): void {

  }

  ConfirmerchangePourcentage(){
    this.parentFunction.emit(true);

    console.log(this.hero.pourcentage);
    const formData = new FormData();
    formData.append('livreur',JSON.stringify(this.hero))
    this.livreurService.changeLivreur(formData).subscribe(data=>{
      console.log(data);

    })
    
    
  }

}
