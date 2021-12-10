
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FournisseurService } from '../service/fournisseur.service';

@Component({
  selector: 'app-add-fournisseur',
  templateUrl: './add-fournisseur.component.html',
  styleUrls: ['./add-fournisseur.component.css']
})
export class AddFournisseurComponent implements OnInit {

  httpFournisseur: any;

  constructor(private router: Router, private Fournisseurservice: FournisseurService, private formBuilder: FormBuilder) {

  }

  emailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$";
  telPattern = "[0-9]{8}";
  captchaKey = "6LebPGAdAAAAANDjO7cQFalTtuU6LFBBDIHxgY9-";
  emailExist = "";
  ngOnInit(): void {
  }

  addFournisseurForm = this.formBuilder.group({
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
    reCaptcha: ['', Validators.required]

  })



  get f() { return this.addFournisseurForm.controls; }







  addFournisseurSubmit() {
    const formData = new FormData();
    const Fournisseur = this.addFournisseurForm.value;
    formData.append('fournisseur', JSON.stringify(Fournisseur));


    this.Fournisseurservice.addFournisseur(formData).subscribe(data => {
      if (data != null)
        this.router.navigate(['/login']);
      else
        this.emailExist = "Email Already exist ";


    })

  }

}
