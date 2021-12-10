import { Directive } from "@angular/core";
import { AbstractControl, AsyncValidator, AsyncValidatorFn, NG_ASYNC_VALIDATORS, ValidationErrors } from "@angular/forms";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { ClientService } from "src/app/service/client.service";
import { ShowUsersComponent } from "src/app/user/show-users/show-users.component";



export function uniqueEmailValidator(userService: ClientService): AsyncValidatorFn {
    let user=null;
    return (c: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> => {
       
        
      return userService.isEmailExist(c.value).pipe(
        map(users => {
            console.log("he");  
          return users && users.length > 0 ? { 'uniqueEmail': true } : null;
        })
      );
    };
  }

@Directive({
selector:'[uniqueEmail]',
providers:[{provide: NG_ASYNC_VALIDATORS,useExisting:UniqueEmailValidator,multi:true}]

})
export class UniqueEmailValidator implements AsyncValidator{

    constructor(private clientService: ClientService)
    {

    }
    validate(c:AbstractControl):Promise<ValidationErrors|null>|Observable<ValidationErrors|null>{
        console.log("hey");
        return this.clientService.isEmailExist(c.value).pipe(
            map(users=>{
                return users && users.length>0 ? {'uniqueEmail' :true}:null;
            }
        ))
    }



    
}