import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, ControlContainer,FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { MailSenderService } from '../../SharedService/mail-sender.service';
import { Address } from './Address';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
  viewProviders: [{ provide: ControlContainer, useExisting: FormGroupDirective }]
})
export class FormComponent implements OnInit {

  @Input() address!: Address;
  form!: FormGroup;
  constructor(
    private ctrlContainer: FormGroupDirective,
    private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.form = this.ctrlContainer.form;

    this.form.addControl('addressForm',
      this.formBuilder.group({
        "name": ['', [Validators.required, Validators.pattern('^[A-Z].*')]],
        "email": [
          '',
          [
            Validators.required,
            Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$'),
          ],
        ],
        "message": [
          '',
          [Validators.required, Validators.maxLength(700)],
        ],
        "sub": ['', [Validators.required, Validators.maxLength(5)]], 
      "file":['',Validators.required]
      }));

    console.log(this.form);
  }
  info (){

  }

  required(controlName: string): boolean {

    return (<FormGroup>this.form.controls['addressForm']).controls[controlName]?.errors?.required
  }
  pattern(controlName: string): boolean {

    return (<FormGroup>this.form.controls['addressForm']).controls[controlName]?.errors?.pattern
  }
  length(controlName: string): boolean {

    return (<FormGroup>this.form.controls['addressForm']).controls[controlName]?.errors?.maxlength
  }



  isInvalid(controlName: string): boolean {

    return (<FormGroup>this.form.controls['addressForm']).controls[controlName].touched
      && (<FormGroup>this.form.controls['addressForm']).controls[controlName].invalid;
  }

  isValid(controlName: string): boolean {
    return (<FormGroup>this.form.controls['addressForm']).controls[controlName].touched
      && (<FormGroup>this.form.controls['addressForm']).controls[controlName].valid;
  }




  file:any;
  public onFileChange(event: any) {
    const reader = new FileReader();
    console.log(event.target.files[0]);
    const file = event.target.files[0];
    reader.readAsDataURL(file);

    reader.onload = () => {
      this.file = reader.result;
   localStorage.setItem('file',String(reader.result))
      console.log(this.file);
   
    };
  }

 

}
