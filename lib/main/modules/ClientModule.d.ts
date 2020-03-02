import { BaseModule as Module } from './BaseModule';
import { ClientAccountRegisterContext } from './../../classes/models/Client/ClientAccountRegisterContext';
import { ClientAccountUpdateContext } from './../../classes/models/Client/ClientAccountUpdateContext';
import { ClientAccountInformation } from './../../classes/models/Client/ClientAccountInformation';
import { ClientOAuthAuthenticationContext } from '../../classes/models/Client/ClientOAuthAuthenticationContext';
import { ClientFacebookAuthenticationContext } from '../../classes/models/Client/ClientFacebookAuthenticationContext';
import { Token } from './../../classes/models/Token/Token';
import { Error } from './../../classes/types/Error';
declare class ClientModule extends Module {
    private static _instance;
    static instance(): ClientModule;
    private constructor();
    registerAccount(context: ClientAccountRegisterContext, onSuccess: () => void, onError: (error: Error) => void): void;
    confirmAccount(token: string, onSuccess: () => void, onError: (error: Error) => void): void;
    activateAccount(email: string, onSuccess: () => void, onError: (error: Error) => void): void;
    signIn(email: string, password: string, onSuccess: () => void, onError: (error: Error) => void): void;
    authenticateByOAuth(accessToken: string, context: ClientOAuthAuthenticationContext, onSuccess: () => void, onError: (error: Error) => void): void;
    authenticateByFacebook(facebookToken: string, context: ClientFacebookAuthenticationContext, onSuccess: () => void, onError: (error: Error) => void): void;
    authenticateByFacebookIfRegistered(facebookToken: string, authID: string, onSuccess: () => void, onError: (error: Error) => void): void;
    isSignedIn(): boolean;
    signOut(): void;
    retrieveToken(onSuccess: (token: Token) => void, onError: (error: Error) => void): void;
    getUUID(): string;
    regenerateUUID(): void;
    getAccount(onSuccess: (clientAccountInformation: ClientAccountInformation) => void, onError: (error: Error) => void): void;
    updateAccount(context: ClientAccountUpdateContext, onSuccess: () => void, onError: (error: Error) => void): void;
    requestPasswordReset(email: string, onSuccess: () => void, onError: (error: Error) => void): void;
    confirmPasswordReset(password: string, token: string, onSuccess: () => void, onError: (error: Error) => void): void;
    changePassword(oldPassword: string, newPassword: string, onSuccess: () => void, onError: (error: Error) => void): void;
    requestEmailChange(email: string, password: string, onSuccess: () => void, onError: (error: Error) => void): void;
    confirmEmailChange(token: string, newsletterAgreement: Boolean, onSuccess: () => void, onError: (error: Error) => void): void;
    requestPhoneUpdate(phone: string, onSuccess: () => void, onError: (error: Error) => void): void;
    confirmPhoneUpdate(phone: string, confirmationCode: string, smsAgreement: Boolean, onSuccess: () => void, onError: (error: Error) => void): void;
    deleteAccount(password: string, onSuccess: () => void, onError: (error: Error) => void): void;
    recognizeAnonymous(email: string | null, customIdentify: string | null, parameters: Record<string, any> | null): void;
}
export { ClientModule };
