import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Address } from 'src/app/shared/GenericForm/form/Address';
import { MailSenderService } from 'src/app/shared/SharedService/mail-sender.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css'],
})
export class ContactComponent implements OnInit {
  address !:Address;
  toggle = false;
  addressForm !: FormGroup;
 
  constructor(private serv : MailSenderService) {}
  get forma()
  {
   return  this.addressForm.controls;
  }
  inf(){
  }
  toggled() {
    this.toggle = !this.toggle;
    console.log(this.toggle);
  }
  ngOnInit(): void {
    this.addressForm= new FormGroup({});
  }
  sub() {
    this.serv
      .sendMail(
        this.addressForm.value.addressForm.email,
        this.addressForm.value.addressForm.sub,
        this.addressForm.value.addressForm.message
        ,localStorage.getItem('file') || ' '
      )
      .subscribe();
  
 console.log(        this.addressForm.value.addressForm.sub )

    }
}
