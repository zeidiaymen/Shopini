import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClientService } from '../Client/client/Services/client.service';
import { user } from '../shared/models/user';
import { UserService } from '../shared/userService/user.service';
import { mustMatch } from '../shared/validators/mustMatchValidator';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  httpClient: any;

  constructor(private router:Router,private service: UserService, private formBuilder: FormBuilder) {

  }

  users: any;
  passwordPattern = "(?=.*[a-z])(?=.*[A-Z]).{8,}";
  emailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$";
  telPattern = "[0-9]{8}";
  captchaKey = "6LebPGAdAAAAANDjO7cQFalTtuU6LFBBDIHxgY9-";
  emailExist="";
  ngOnInit(): void {

  }

  addClientForm = this.formBuilder.group({
    firstName: ['', Validators.required],

    lastName: ['',
      Validators.required
    ],
    email: ['', [
      Validators.required,
      Validators.pattern(this.emailPattern),

    ]],
    password: ['', [
      Validators.required,
      Validators.pattern(this.passwordPattern)
    ]],
    confirmPassword: ['',
      Validators.required,
    ],
    sexe: ['',
      Validators.required
    ],
    tel: ['', [
      Validators.required,
      Validators.pattern(this.telPattern)
    ]],
    picture: ['',
      Validators.required,
    ],
    profession: ['DOCTEUR',
    Validators.required,
  ],
    reCaptcha: ['', Validators.required]

  }, {
    validator: mustMatch('password', "confirmPassword"),

  })



  get f() { return this.addClientForm.controls; }




  imgURL: any;
  userFile: any;
  message: any;
  public imagePath: any;

  onSelectFile(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.userFile = file;


     




      var reader = new FileReader();

      this.imagePath = file;
      reader.readAsDataURL(file);
      reader.onload = (_event) => {
        this.imgURL = reader.result;

      }
    }
    
   



  }

  addClientSubmit() {
    const formData = new FormData();
    formData.append('file', this.userFile);
    const user = this.addClientForm.value;
    user.picture=null;
    formData.append('client', JSON.stringify(user));


    this.service.addClient(formData).subscribe(data => {
      if(data!=null){
      this.router.navigate(['/login']);
      alert("Email verification sent to you")
    }

      else
      this.emailExist="Email Already exist ";


    })
    
  }




}
