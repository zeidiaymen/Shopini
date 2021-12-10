import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from '../service/admin.service';




@Component({
  selector: 'app-add-admin',
  templateUrl: './add-admin.component.html',
  styleUrls: ['./add-admin.component.css']
})
export class AddAdminComponent implements OnInit {

  httpAdmin: any;

  constructor(private router: Router, private adminservice: AdminService, private formBuilder: FormBuilder) {

  }

  emailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$";
  telPattern = "[0-9]{8}";
  captchaKey = "6LebPGAdAAAAANDjO7cQFalTtuU6LFBBDIHxgY9-";
  emailExist = "";
  ngOnInit(): void {
  }

  addAdminForm = this.formBuilder.group({
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



  get f() { return this.addAdminForm.controls; } 







  addAdminSubmit() {
    const formData = new FormData();
    const admin = this.addAdminForm.value;
    formData.append('admin', JSON.stringify(admin));


    this.adminservice.addAdmin(formData).subscribe(data => {
      if (data != null)
        this.router.navigate(['/login']);
      else
        this.emailExist = "Email Already exist ";


    })

  }



}


