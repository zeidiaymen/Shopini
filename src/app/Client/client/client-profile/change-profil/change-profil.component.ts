import { Component, OnInit } from '@angular/core';
import {  FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { user } from 'src/app/shared/models/user';
import { UserService } from 'src/app/shared/userService/user.service';
@Component({
  selector: 'app-change-profil',
  templateUrl: './change-profil.component.html',
  styleUrls: ['./change-profil.component.css']
})
export class ChangeProfilComponent implements OnInit {

  captchaKey = "6LebPGAdAAAAANDjO7cQFalTtuU6LFBBDIHxgY9-";
  currentUser!: user
  baseUrl: any;
  pictureToUpdate!: any;
  token!:any;
  twoFactorAuthenticateMessage="";

  constructor(private router:Router,private userService: UserService) {

  }
  captcha = new FormControl('', Validators.required);




  ngOnInit(): void {
    this.token = localStorage.getItem('token');
    this.userService.getUserFromToken(this.token).subscribe(data => {
      if(data.twoFactorAuthentication)
      this.twoFactorAuthenticateMessage="Desactivate Two factor authentication"
      else 
      this.twoFactorAuthenticateMessage="Activate Two factor authentication"
      this.currentUser = data;
      this.userToUpdate = data;
      this.baseUrl = this.userService.baseUrl;
    })
  }


  userToUpdate = {
    email: "",
    firstName: "",
    lastName: "",
    picture:""
  }










  message: any;
  public imagePath: any;

  onSelectFile(event: any) {
    
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.pictureToUpdate = file;

      const formData = new FormData();
      formData.append('file', this.pictureToUpdate);
      this.userService.updateUserPicture(formData,this.token).subscribe(data =>{
      //  this.router.navigate(['userProfile']);   
           

      });

    }


  }

  updateClientSubmit() {



    const formData = new FormData();
    const user = this.userToUpdate;
    formData.append('user', JSON.stringify(user));




    this.userService.updateUser(user).subscribe(data => {
      console.log(JSON.stringify(user));
      //this.router.navigate(['/showUsers']);
    })
  }

  twoFactorAuthenticate(){
    const formData = new FormData();
    formData.append('token',this.token)

    this.userService.twoFactorAuthenticate(formData).subscribe(data=>{

      if(data){
        alert("Activated")
      this.twoFactorAuthenticateMessage="Desactivate Two factor authentication"
  }
      else {
      this.twoFactorAuthenticateMessage="Activate Two factor authentication"
      alert("Desactivated")

    }

    })

  }





}



