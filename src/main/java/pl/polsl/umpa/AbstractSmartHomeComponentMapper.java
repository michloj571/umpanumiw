package pl.polsl.umpa;

public class AbstractSmartHomeComponentMapper {
    protected final SmartHomeComponentStateDto mapComponentState(
            AbstractSmartHomeComponentState abstractSmartHomeComponentState, String componentName
    ) {
        return new SmartHomeComponentStateDto(
                componentName,
                abstractSmartHomeComponentState.getState(),
                abstractSmartHomeComponentState.getRecordDate()
        );
    }
}
