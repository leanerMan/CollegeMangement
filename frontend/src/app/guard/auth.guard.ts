import { CanActivateFn, Router } from '@angular/router';
import { StorageService } from '../core/storage.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const authenticated = inject(StorageService).isLoggedIn();
  if (!authenticated) {
    inject(Router).navigate(['login'])
    return false
  }
  return true;
};
