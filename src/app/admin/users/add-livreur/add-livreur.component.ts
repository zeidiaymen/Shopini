import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LivreurService } from '../service/livreur.service';

@Component({
  selector: 'app-add-livreur',
  templateUrl: './add-livreur.component.html',
  styleUrls: ['./add-livreur.component.css']
})
export class AddLivreurComponent implements OnInit {
  httpLivreur: any;

  constructor(private router: Router, private Livreurservice: LivreurService, private formBuilder: FormBuilder) {

  }

  emailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$";
  telPattern = "[0-9]{8}";
  captchaKey = "6LebPGAdAAAAANDjO7cQFalTtuU6LFBBDIHxgY9-";
  emailExist = "";
  ngOnInit(): void {
  }

  addLivreurForm = this.formBuilder.group({
    firstName: ['', 
    Validators.required],

    lastName: ['',
      Validators.required
    ],
    email: ['', [
      Validators.required,
      Validators.pattern(this.emailPattern),

    ]],    
    sexe: ['',
      Validators.required
    ],
    tel: ['', [
      Validators.required,
      Validators.pattern(this.telPattern)
    ]],    
    pourcentage: ['', 
    Validators.required],
    reCaptcha: ['', Validators.required]

  })



  get f() { return this.addLivreurForm.controls; }







  addLivreurSubmit() {
    const formData = new FormData();
    const Livreur = this.addLivreurForm.value;
    formData.append('livreur', JSON.stringify(Livreur));


    this.Livreurservice.addLivreur(formData).subscribe(data => {
      if (data != null)
        this.router.navigate(['/login']);
      else
        this.emailExist = "Email Already exist";
    })

  }



}