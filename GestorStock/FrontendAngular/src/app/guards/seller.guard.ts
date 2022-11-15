import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { LoginUser } from '../models/Usuario.model';
import { Roles } from '../utils/roles.enum';

@Injectable({
  providedIn: 'root',
})
export class SellerGuard implements CanActivate {
  private isSeller() {
    let user: LoginUser | null = JSON.parse(
      sessionStorage.getItem('loggedInUser') || 'null'
    );
    if (!user) return false;
    for (const authority of user.authorities) {
      if (authority.authority === Roles.ROLE_SELLER) {
        return true;
      }
    }
    return false;
  }

  constructor(private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    if (!this.isSeller()) {
      this.router.navigate(['/login']).then(() => false);
    }
    return true;
  }
}
