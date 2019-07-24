package reflect.demo;

import reflect.abs.AbstractBase;
import reflect.service.IChannelService;
import reflect.service.IMessageService;

public class SomeOne extends AbstractBase implements IMessageService, IChannelService {
    @Override
    public boolean connect() {
        return false;
    }

    @Override
    public void send() {
        System.out.println("");
    }
}
